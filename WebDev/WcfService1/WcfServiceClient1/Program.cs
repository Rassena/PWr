using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WcfServiceClient1.ServiceReference1;

namespace WcfServiceClient1
{
    class Program
    {
        static void Main(string[] args)
        {
            //Instancja WCF proxy

            //CalculatorClient myClient = new CalculatorClient();

            CalculatorClient client1 = new CalculatorClient("WSHttpBinding_ICalculator");
            CalculatorClient client2 = new CalculatorClient("BasicHttpBinding_ICalculator");
            CalculatorClient client3 = new CalculatorClient("myEndpoint3");



            //Wywolanie operacji

            double value1 = 3;
            double value2 = 2;

            double result = client1.Add(value1, value2);
            double sum;
            Console.WriteLine("Client1: {0} + {1} = {2}",value1,value2,result);
            
            sum=client1.Sumarize(1);
            Console.WriteLine("Sumarize po kliencie 1: {0}", sum);
            sum = client1.Sumarize(1);
            Console.WriteLine("Sumarize po kliencie 1: {0}", sum);


            result = client2.Sub(value1, value2);
            Console.WriteLine("Client2: {0} - {1} = {2}", value1, value2, result);

            sum = client2.Sumarize(2);
            Console.WriteLine("Sumarize po kliencie 2: {0}", sum);
            sum = client2.Sumarize(2);
            Console.WriteLine("Sumarize po kliencie 2: {0}", sum);

            result = client3.Multiply(value1, value2);
            Console.WriteLine("Client3: {0} * {1} = {2}", value1, value2, result);

            sum = client3.Sumarize(3);
            Console.WriteLine("Sumarize po kliencie 3: {0}", sum);
            sum = client3.Sumarize(3);
            Console.WriteLine("Sumarize po kliencie 3: {0}", sum);


            //Zamkniecie klienta
            client1.Close();
            client2.Close();
            client3.Close();

        }
    }
}
