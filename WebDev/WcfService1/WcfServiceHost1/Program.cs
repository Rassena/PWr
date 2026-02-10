using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Description;
using System.Text;
using System.Threading.Tasks;
using WcfServiceContract1;

namespace WcfServiceHost1
{
    class Program
    {
        static void Main(string[] args)
        {
            // URI bazowego adresu
            Uri baseAddress = new Uri("http://localhost:10001/NazwaBazowa");

            //Instacja serwisu
            ServiceHost myHost = new ServiceHost(typeof(MyCalculator), baseAddress);

            //Endpoint
            WSHttpBinding myBinding = new WSHttpBinding();
            ServiceEndpoint endpoint1 = myHost.AddServiceEndpoint(typeof(ICalculator), myBinding, "endpoint1");

            //Metadane
            ServiceMetadataBehavior smb = new ServiceMetadataBehavior();
            smb.HttpGetEnabled = true;
            myHost.Description.Behaviors.Add(smb);

            try
            {
                BasicHttpBinding binding2 = new BasicHttpBinding();
                ServiceEndpoint endpoint2 = myHost.AddServiceEndpoint(typeof(ICalculator), binding2, "endpoint2");

                ServiceEndpoint endpoint3 = myHost.Description.Endpoints.Find(new Uri("http://localhost:10001/NazwaBazowa/endpoint3"));

                //Urochom Serwis
                myHost.Open();

                Console.WriteLine("\n---> Endpointy:");
                Console.WriteLine("\nServices endpoint {0}:", endpoint1.Name);
                Console.WriteLine("Binding: {0}", endpoint1.Binding.ToString());
                Console.WriteLine("ListenUei: {0}", endpoint1.ListenUri.ToString());

                Console.WriteLine("\nServices endpoint {0}:", endpoint2.Name);
                Console.WriteLine("Binding: {0}", endpoint2.Binding.ToString());
                Console.WriteLine("ListenUei: {0}", endpoint2.ListenUri.ToString());

                Console.WriteLine("\nServices endpoint {0}:", endpoint3.Name);
                Console.WriteLine("Binding: {0}", endpoint3.Binding.ToString());
                Console.WriteLine("ListenUei: {0}", endpoint3.ListenUri.ToString());

                Console.WriteLine("\n");
                Console.WriteLine("Serwis jest uruchomiony.");
                Console.WriteLine("Nacisnij <ENTER> aby zakonczyc.");
                Console.WriteLine();
                Console.ReadLine();
                myHost.Close();
            }
            catch (CommunicationException ce)
            {
                Console.WriteLine("Wystapil wyjatek: {0}", ce.Message);
                myHost.Abort();
            }
        }
    }
}
