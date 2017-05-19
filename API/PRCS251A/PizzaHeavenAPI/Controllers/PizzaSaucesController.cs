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
    public class PizzaSaucesController : ApiController
    {
        private SauceConn db = new SauceConn();

        // GET: api/PizzaSauces
        public IQueryable<PizzaSauce> GetPizzaSauces()
        {
            return db.PizzaSauces;
        }

        // GET: api/PizzaSauces/5
        [ResponseType(typeof(PizzaSauce))]
        public IHttpActionResult GetPizzaSauce(string id)
        {
            PizzaSauce pizzaSauce = db.PizzaSauces.Find(id);
            if (pizzaSauce == null)
            {
                return NotFound();
            }

            return Ok(pizzaSauce);
        }

        // PUT: api/PizzaSauces/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPizzaSauce(string id, PizzaSauce pizzaSauce)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pizzaSauce.SauceName)
            {
                return BadRequest();
            }

            db.Entry(pizzaSauce).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PizzaSauceExists(id))
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

        // POST: api/PizzaSauces
        [ResponseType(typeof(PizzaSauce))]
        public IHttpActionResult PostPizzaSauce(PizzaSauce pizzaSauce)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PizzaSauces.Add(pizzaSauce);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (PizzaSauceExists(pizzaSauce.SauceName))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = pizzaSauce.SauceName }, pizzaSauce);
        }

        // DELETE: api/PizzaSauces/5
        [ResponseType(typeof(PizzaSauce))]
        public IHttpActionResult DeletePizzaSauce(string id)
        {
            PizzaSauce pizzaSauce = db.PizzaSauces.Find(id);
            if (pizzaSauce == null)
            {
                return NotFound();
            }

            db.PizzaSauces.Remove(pizzaSauce);
            db.SaveChanges();

            return Ok(pizzaSauce);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PizzaSauceExists(string id)
        {
            return db.PizzaSauces.Count(e => e.SauceName == id) > 0;
        }
    }
}