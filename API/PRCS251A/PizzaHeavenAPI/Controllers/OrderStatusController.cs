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
    public class OrderStatusController : ApiController
    {
        private StatusConnection db = new StatusConnection();

        // GET: api/OrderStatus
        public IQueryable<OrderStatus> GetOrderStatus()
        {
            return db.OrderStatus;
        }

        // GET: api/OrderStatus/5
        [ResponseType(typeof(OrderStatus))]
        public IHttpActionResult GetOrderStatus(string id)
        {
            OrderStatus orderStatus = db.OrderStatus.Find(id);
            if (orderStatus == null)
            {
                return NotFound();
            }

            return Ok(orderStatus);
        }

        // PUT: api/OrderStatus/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutOrderStatus(string id, OrderStatus orderStatus)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != orderStatus.Status)
            {
                return BadRequest();
            }

            db.Entry(orderStatus).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!OrderStatusExists(id))
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

        // POST: api/OrderStatus
        [ResponseType(typeof(OrderStatus))]
        public IHttpActionResult PostOrderStatus(OrderStatus orderStatus)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.OrderStatus.Add(orderStatus);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (OrderStatusExists(orderStatus.Status))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = orderStatus.Status }, orderStatus);
        }

        // DELETE: api/OrderStatus/5
        [ResponseType(typeof(OrderStatus))]
        public IHttpActionResult DeleteOrderStatus(string id)
        {
            OrderStatus orderStatus = db.OrderStatus.Find(id);
            if (orderStatus == null)
            {
                return NotFound();
            }

            db.OrderStatus.Remove(orderStatus);
            db.SaveChanges();

            return Ok(orderStatus);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool OrderStatusExists(string id)
        {
            return db.OrderStatus.Count(e => e.Status == id) > 0;
        }
    }
}