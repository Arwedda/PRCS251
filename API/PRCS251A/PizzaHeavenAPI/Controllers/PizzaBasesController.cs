using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using PizzaHeavenAPI.Models;

namespace PizzaHeavenAPI.Controllers
{
    public class PizzaBasesController : ApiController
    {
        private BaseConnection db = new BaseConnection();

        // GET: api/PizzaBases
        public IQueryable<PizzaBase> GetPizzaBases()
        {
            return db.PizzaBases;
        }

        // GET: api/PizzaBases/5
        [ResponseType(typeof(PizzaBase))]
        public IHttpActionResult GetPizzaBase(string id)
        {
            PizzaBase pizzaBase = db.PizzaBases.Find(id);
            if (pizzaBase == null)
            {
                return NotFound();
            }

            return Ok(pizzaBase);
        }

        // PUT: api/PizzaBases/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPizzaBase(string id, PizzaBase pizzaBase)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pizzaBase.BaseName)
            {
                return BadRequest();
            }

            db.Entry(pizzaBase).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PizzaBaseExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/PizzaBases
        [ResponseType(typeof(PizzaBase))]
        public IHttpActionResult PostPizzaBase(PizzaBase pizzaBase)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PizzaBases.Add(pizzaBase);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (PizzaBaseExists(pizzaBase.BaseName))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = pizzaBase.BaseName }, pizzaBase);
        }

        // DELETE: api/PizzaBases/5
        [ResponseType(typeof(PizzaBase))]
        public IHttpActionResult DeletePizzaBase(string id)
        {
            PizzaBase pizzaBase = db.PizzaBases.Find(id);
            if (pizzaBase == null)
            {
                return NotFound();
            }

            db.PizzaBases.Remove(pizzaBase);
            db.SaveChanges();

            return Ok(pizzaBase);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PizzaBaseExists(string id)
        {
            return db.PizzaBases.Count(e => e.BaseName == id) > 0;
        }
    }
}