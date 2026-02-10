using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;


namespace MyWebService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.
    
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class MyRestService : IRestService
    {
        private static List<Book> books;

        public MyRestService()
        {
            books = new List<Book>()
            {
                new Book { title = "pierwsza ksiazka", autor = "pierwszy autor", id = 1, pages = 310 },
                new Book { title = "druga ksiazka", autor = "drugi autor", id = 2, pages = 110 },
                new Book { title = "trzecia ksiazka", autor = "pierwszy autor", id = 3, pages = 710 }
            };
            
        }

        public string addJson(Book item)
        {
            return addXml(item);
        }

        public string addXml(Book item)
        {
            int newIdx = books.Count;

            if (item == null)
                throw new WebFaultException<string>("400: BadRequest", System.Net.HttpStatusCode.BadRequest);
            int idx = books.FindIndex(b => b.id == newIdx);
            if (idx == -1)
            {
                item.id = newIdx;
                books.Add(item);
                return "Added item with ID=" + item.id;
            }
            else
                throw new WebFaultException<string>("409: Conflict", System.Net.HttpStatusCode.Conflict);
        }

        public string deleteJson(string Id)
        {
            return deleteXml(Id);
        }

        public string deleteXml(string Id)
        {
            int intId = int.Parse(Id);
            int idx = books.FindIndex(b => b.id == intId);
            if (idx == -1)
                throw new WebFaultException<string>("404: Not Found", System.Net.HttpStatusCode.NotFound);
             books.RemoveAt(idx);
            return "Removed item with Id= " + Id;
        }

        public List<Book> getAllJson()
        {
            return getAllXml();
        }

        public List<Book> getAllXml()
        {
            return books;
        }

        public Book getByIdJson(string Id)
        {
            return getByIdXml(Id);
        }

        public Book getByIdXml(string Id)
        {
            int intId = int.Parse(Id);
            int idx = books.FindIndex(b => b.id == intId);
            if (idx == -1)
                throw new WebFaultException<string>("404: Not Found", System.Net.HttpStatusCode.NotFound);
            return books.ElementAt(idx);
        }
    }
}
