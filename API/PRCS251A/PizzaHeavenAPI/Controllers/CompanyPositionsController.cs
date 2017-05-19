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
    public class CompanyPositionsController : ApiController
    {
        private CompanyPositionConnection db = new CompanyPositionConnection();

        // GET: api/CompanyPositions
        public IQueryable<CompanyPosition> GetCompanyPositions()
        {
            return db.CompanyPositions;
        }

        // GET: api/CompanyPositions/5
        [ResponseType(typeof(CompanyPosition))]
        public IHttpActionResult GetCompanyPosition(string id)
        {
            CompanyPosition companyPosition = db.CompanyPositions.Find(id);
            if (companyPosition == null)
            {
                return NotFound();
            }

            return Ok(companyPosition);
        }

        // PUT: api/CompanyPositions/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutCompanyPosition(string id, CompanyPosition companyPosition)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != companyPosition.Name)
            {
                return BadRequest();
            }

            db.Entry(companyPosition).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CompanyPositionExists(id))
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

        // POST: api/CompanyPositions
        [ResponseType(typeof(CompanyPosition))]
        public IHttpActionResult PostCompanyPosition(CompanyPosition companyPosition)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.CompanyPositions.Add(companyPosition);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (CompanyPositionExists(companyPosition.Name))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = companyPosition.Name }, companyPosition);
        }

        // DELETE: api/CompanyPositions/5
        [ResponseType(typeof(CompanyPosition))]
        public IHttpActionResult DeleteCompanyPosition(string id)
        {
            CompanyPosition companyPosition = db.CompanyPositions.Find(id);
            if (companyPosition == null)
            {
                return NotFound();
            }

            db.CompanyPositions.Remove(companyPosition);
            db.SaveChanges();

            return Ok(companyPosition);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool CompanyPositionExists(string id)
        {
            return db.CompanyPositions.Count(e => e.Name == id) > 0;
        }
    }
}