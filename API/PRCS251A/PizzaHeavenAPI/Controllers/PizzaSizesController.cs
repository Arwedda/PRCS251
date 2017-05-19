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
    public class PizzaSizesController : ApiController
    {
        private SizeConnection db = new SizeConnection();

        // GET: api/PizzaSizes
        public IQueryable<PizzaSize> GetPizzaSizes()
        {
            return db.PizzaSizes;
        }

        // GET: api/PizzaSizes/5
        [ResponseType(typeof(PizzaSize))]
        public IHttpActionResult GetPizzaSize(string id)
        {
            PizzaSize pizzaSize = db.PizzaSizes.Find(id);
            if (pizzaSize == null)
            {
                return NotFound();
            }

            return Ok(pizzaSize);
        }

        // PUT: api/PizzaSizes/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPizzaSize(string id, PizzaSize pizzaSize)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pizzaSize.SizeName)
            {
                return BadRequest();
            }

            db.Entry(pizzaSize).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PizzaSizeExists(id))
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

        // POST: api/PizzaSizes
        [ResponseType(typeof(PizzaSize))]
        public IHttpActionResult PostPizzaSize(PizzaSize pizzaSize)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PizzaSizes.Add(pizzaSize);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (PizzaSizeExists(pizzaSize.SizeName))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = pizzaSize.SizeName }, pizzaSize);
        }

        // DELETE: api/PizzaSizes/5
        [ResponseType(typeof(PizzaSize))]
        public IHttpActionResult DeletePizzaSize(string id)
        {
            PizzaSize pizzaSize = db.PizzaSizes.Find(id);
            if (pizzaSize == null)
            {
                return NotFound();
            }

            db.PizzaSizes.Remove(pizzaSize);
            db.SaveChanges();

            return Ok(pizzaSize);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PizzaSizeExists(string id)
        {
            return db.PizzaSizes.Count(e => e.SizeName == id) > 0;
        }
    }
}