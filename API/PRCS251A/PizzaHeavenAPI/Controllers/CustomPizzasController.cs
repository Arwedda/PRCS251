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
    public class CustomPizzasController : ApiController
    {
        private CustomisedPizzaConnection db = new CustomisedPizzaConnection();

        // GET: api/CustomPizzas
        public IQueryable<CustomPizza> GetCustomPizzas()
        {
            return db.CustomPizzas;
        }

        // GET: api/CustomPizzas/5
        [ResponseType(typeof(CustomPizza))]
        public IHttpActionResult GetCustomPizza(string id)
        {
            CustomPizza customPizza = db.CustomPizzas.Find(id);
            if (customPizza == null)
            {
                return NotFound();
            }

            return Ok(customPizza);
        }

        // PUT: api/CustomPizzas/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCustomPizza(string id, CustomPizza customPizza)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != customPizza.CustomPizzaName)
            {
                return BadRequest();
            }

            db.Entry(customPizza).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CustomPizzaExists(id))
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

        // POST: api/CustomPizzas
        [ResponseType(typeof(CustomPizza))]
        public IHttpActionResult PostCustomPizza(CustomPizza customPizza)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.CustomPizzas.Add(customPizza);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (CustomPizzaExists(customPizza.CustomPizzaName))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = customPizza.CustomPizzaName }, customPizza);
        }

        // DELETE: api/CustomPizzas/5
        [ResponseType(typeof(CustomPizza))]
        public IHttpActionResult DeleteCustomPizza(string id)
        {
            CustomPizza customPizza = db.CustomPizzas.Find(id);
            if (customPizza == null)
            {
                return NotFound();
            }

            db.CustomPizzas.Remove(customPizza);
            db.SaveChanges();

            return Ok(customPizza);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool CustomPizzaExists(string id)
        {
            return db.CustomPizzas.Count(e => e.CustomPizzaName == id) > 0;
        }
    }
}