using Cassandra;
using System;
using System.Collections.Generic;
using System.Text;

namespace GUI
{
    public class Shot
    {
        public long id { get; set; }
        public string name { get; set; }
        public bool accessible { get; set; }
        public long refund { get; set; }
    }

}
