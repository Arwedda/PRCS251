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
    public class PercentageOffersController : ApiController
    {
        private PercentageOfferConnection db = new PercentageOfferConnection();

        // GET: api/PercentageOffers
        public IQueryable<PercentageOffer> GetPercentageOffers()
        {
            return db.PercentageOffers;
        }

        // GET: api/PercentageOffers/5
        [ResponseType(typeof(PercentageOffer))]
        public IHttpActionResult GetPercentageOffer(short id)
        {
            PercentageOffer percentageOffer = db.PercentageOffers.Find(id);
            if (percentageOffer == null)
            {
                return NotFound();
            }

            return Ok(percentageOffer);
        }

        // PUT: api/PercentageOffers/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPercentageOffer(short id, PercentageOffer percentageOffer)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != percentageOffer.OfferID)
            {
                return BadRequest();
            }

            db.Entry(percentageOffer).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PercentageOfferExists(id))
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

        // POST: api/PercentageOffers
        [ResponseType(typeof(PercentageOffer))]
        public IHttpActionResult PostPercentageOffer(PercentageOffer percentageOffer)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.PercentageOffers.Add(percentageOffer);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (PercentageOfferExists(percentageOffer.OfferID))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = percentageOffer.OfferID }, percentageOffer);
        }

        // DELETE: api/PercentageOffers/5
        [ResponseType(typeof(PercentageOffer))]
        public IHttpActionResult DeletePercentageOffer(short id)
        {
            PercentageOffer percentageOffer = db.PercentageOffers.Find(id);
            if (percentageOffer == null)
            {
                return NotFound();
            }

            db.PercentageOffers.Remove(percentageOffer);
            db.SaveChanges();

            return Ok(percentageOffer);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PercentageOfferExists(short id)
        {
            return db.PercentageOffers.Count(e => e.OfferID == id) > 0;
        }
    }
}