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
    public class PizzaCheeseController : ApiController
    {
        private CheeseConnection db = new CheeseConnection();

        // GET: api/PizzaCheese
        public IQueryable<PizzaCheese> GetPizzaCheese()
        {
            return db.PizzaCheese;
        }

        // GET: api/PizzaCheese/5
        [ResponseType(typeof(PizzaCheese))]
        public IHttpActionResult GetPizzaCheese(string id)
        {
            PizzaCheese pizzaCheese = db.PizzaCheese.Find(id);
            if (pizzaCheese == null)
            {
                return NotFound();
            }

            return Ok(pizzaCheese);
        }

        // PUT: api/PizzaCheese/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPizzaCheese(string id, PizzaCheese pizzaCheese)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pizzaCheese.CheeseName)
            {
                return BadRequest();
            }

            db.Entry(pizzaCheese).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PizzaCheeseExists(id))
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

        // POST: api/PizzaCheese
        [ResponseType(typeof(PizzaCheese))]
        public IHttpActionResult PostPizzaCheese(PizzaCheese pizzaCheese)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PizzaCheese.Add(pizzaCheese);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (PizzaCheeseExists(pizzaCheese.CheeseName))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = pizzaCheese.CheeseName }, pizzaCheese);
        }

        // DELETE: api/PizzaCheese/5
        [ResponseType(typeof(PizzaCheese))]
        public IHttpActionResult DeletePizzaCheese(string id)
        {
            PizzaCheese pizzaCheese = db.PizzaCheese.Find(id);
            if (pizzaCheese == null)
            {
                return NotFound();
            }

            db.PizzaCheese.Remove(pizzaCheese);
            db.SaveChanges();

            return Ok(pizzaCheese);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PizzaCheeseExists(string id)
        {
            return db.PizzaCheese.Count(e => e.CheeseName == id) > 0;
        }
    }
}