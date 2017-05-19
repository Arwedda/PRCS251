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
    public class PizzaCrustsController : ApiController
    {
        private CrustConnection db = new CrustConnection();

        // GET: api/PizzaCrusts
        public IQueryable<PizzaCrust> GetPizzaCrusts()
        {
            return db.PizzaCrusts;
        }

        // GET: api/PizzaCrusts/5
        [ResponseType(typeof(PizzaCrust))]
        public IHttpActionResult GetPizzaCrust(string id)
        {
            PizzaCrust pizzaCrust = db.PizzaCrusts.Find(id);
            if (pizzaCrust == null)
            {
                return NotFound();
            }

            return Ok(pizzaCrust);
        }

        // PUT: api/PizzaCrusts/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPizzaCrust(string id, PizzaCrust pizzaCrust)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != pizzaCrust.CrustName)
            {
                return BadRequest();
            }

            db.Entry(pizzaCrust).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PizzaCrustExists(id))
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

        // POST: api/PizzaCrusts
        [ResponseType(typeof(PizzaCrust))]
        public IHttpActionResult PostPizzaCrust(PizzaCrust pizzaCrust)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PizzaCrusts.Add(pizzaCrust);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (PizzaCrustExists(pizzaCrust.CrustName))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = pizzaCrust.CrustName }, pizzaCrust);
        }

        // DELETE: api/PizzaCrusts/5
        [ResponseType(typeof(PizzaCrust))]
        public IHttpActionResult DeletePizzaCrust(string id)
        {
            PizzaCrust pizzaCrust = db.PizzaCrusts.Find(id);
            if (pizzaCrust == null)
            {
                return NotFound();
            }

            db.PizzaCrusts.Remove(pizzaCrust);
            db.SaveChanges();

            return Ok(pizzaCrust);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PizzaCrustExists(string id)
        {
            return db.PizzaCrusts.Count(e => e.CrustName == id) > 0;
        }
    }
}