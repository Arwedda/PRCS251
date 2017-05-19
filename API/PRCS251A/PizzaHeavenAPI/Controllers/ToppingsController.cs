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
    public class ToppingsController : ApiController
    {
        private ToppingsEntityConnection db = new ToppingsEntityConnection();

        // GET: api/Toppings
        public IQueryable<Topping> GetToppings()
        {
            return db.Toppings;
        }

        // GET: api/Toppings/5
        [ResponseType(typeof(Topping))]
        public IHttpActionResult GetTopping(string id)
        {
            Topping topping = db.Toppings.Find(id);
            if (topping == null)
            {
                return NotFound();
            }

            return Ok(topping);
        }

        // PUT: api/Toppings/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutTopping(string id, Topping topping)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != topping.ToppingName)
            {
                return BadRequest();
            }

            db.Entry(topping).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ToppingExists(id))
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

        // POST: api/Toppings
        [ResponseType(typeof(Topping))]
        public IHttpActionResult PostTopping(Topping topping)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Toppings.Add(topping);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (ToppingExists(topping.ToppingName))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = topping.ToppingName }, topping);
        }

        // DELETE: api/Toppings/5
        [ResponseType(typeof(Topping))]
        public IHttpActionResult DeleteTopping(string id)
        {
            Topping topping = db.Toppings.Find(id);
            if (topping == null)
            {
                return NotFound();
            }

            db.Toppings.Remove(topping);
            db.SaveChanges();

            return Ok(topping);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ToppingExists(string id)
        {
            return db.Toppings.Count(e => e.ToppingName == id) > 0;
        }
    }
}