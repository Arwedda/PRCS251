//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace PizzaHeavenAPI.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Pizza
    {
        public string Name { get; set; }
        public string Description { get; set; }
        public Nullable<decimal> SmallPrice { get; set; }
        public Nullable<decimal> HeatRating { get; set; }
        public string Image { get; set; }
        public Nullable<decimal> MediumPrice { get; set; }
        public Nullable<decimal> LargePrice { get; set; }
    }
}
