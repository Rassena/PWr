using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace MyWebService
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IRestService
    {

        [OperationContract]
        [WebGet(UriTemplate = "/books")]
        List<Book> getAllXml();

        [OperationContract]
        [WebGet(UriTemplate = "/books/{id}", ResponseFormat = WebMessageFormat.Xml)]
        Book getByIdXml(string Id);

        [OperationContract]
        [WebInvoke(UriTemplate = "/books", Method = "POST", RequestFormat = WebMessageFormat.Xml)]
        string addXml(Book item);

        [OperationContract]
        [WebInvoke(UriTemplate = "/books/{id}", Method = "DELETE")]
        string deleteXml(string Id);

        // TODO: Add your service operations here

        [OperationContract]
        [WebGet(UriTemplate = "/json/books", ResponseFormat = WebMessageFormat.Json)]
        List<Book> getAllJson();

        [OperationContract]
        [WebGet(UriTemplate = "/json/books/{id}", ResponseFormat = WebMessageFormat.Json)]
        Book getByIdJson(string Id);

        [OperationContract]
        [WebInvoke(UriTemplate = "/json/books", Method = "POST", RequestFormat = WebMessageFormat.Json)]
        string addJson(Book item);

        [OperationContract]
        [WebInvoke(UriTemplate = "/json/books/{id}", Method = "DELETE", ResponseFormat = WebMessageFormat.Json)]
        string deleteJson(string Id);

    }


    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    [DataContract]
    public class Book
    {
        [DataMember(Order = 0)]
        public int id { get; set; }
        [DataMember(Order =1)]
        public string title { get; set; }
        [DataMember(Order = 2)]
        public string autor { get; set; }

        [DataMember(Order = 3)]
        public int pages { get; set; }


    }
}
