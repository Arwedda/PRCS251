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
    public class SidesController : ApiController
    {
        private SidesConnection db = new SidesConnection();

        // GET: api/Sides
        public IQueryable<Side> GetSides()
        {
            return db.Sides;
        }

        // GET: api/Sides/5
        [ResponseType(typeof(Side))]
        public IHttpActionResult GetSide(string id)
        {
            Side side = db.Sides.Find(id);
            if (side == null)
            {
                return NotFound();
            }

            return Ok(side);
        }

        // PUT: api/Sides/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSide(string id, Side side)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != side.Name)
            {
                return BadRequest();
            }

            db.Entry(side).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SideExists(id))
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

        // POST: api/Sides
        [ResponseType(typeof(Side))]
        public IHttpActionResult PostSide(Side side)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Sides.Add(side);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (SideExists(side.Name))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = side.Name }, side);
        }

        // DELETE: api/Sides/5
        [ResponseType(typeof(Side))]
        public IHttpActionResult DeleteSide(string id)
        {
            Side side = db.Sides.Find(id);
            if (side == null)
            {
                return NotFound();
            }

            db.Sides.Remove(side);
            db.SaveChanges();

            return Ok(side);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool SideExists(string id)
        {
            return db.Sides.Count(e => e.Name == id) > 0;
        }
    }
}