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
    public class StaffController : ApiController
    {
        private StaffEntityConnection db = new StaffEntityConnection();

        // GET: api/Staff
        public IQueryable<Staff> GetStaff()
        {
            return db.Staff;
        }

        // GET: api/Staff/5
        [ResponseType(typeof(Staff))]
        public IHttpActionResult GetStaff(short id)
        {
            Staff staff = db.Staff.Find(id);
            if (staff == null)
            {
                return NotFound();
            }

            return Ok(staff);
        }

        // PUT: api/Staff/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutStaff(short id, Staff staff)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != staff.StaffID)
            {
                return BadRequest();
            }

            db.Entry(staff).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!StaffExists(id))
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

        // POST: api/Staff
        [ResponseType(typeof(Staff))]
        public IHttpActionResult PostStaff(Staff staff)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Staff.Add(staff);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (StaffExists(staff.StaffID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = staff.StaffID }, staff);
        }

        // DELETE: api/Staff/5
        [ResponseType(typeof(Staff))]
        public IHttpActionResult DeleteStaff(short id)
        {
            Staff staff = db.Staff.Find(id);
            if (staff == null)
            {
                return NotFound();
            }

            db.Staff.Remove(staff);
            db.SaveChanges();

            return Ok(staff);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool StaffExists(short id)
        {
            return db.Staff.Count(e => e.StaffID == id) > 0;
        }
    }
}