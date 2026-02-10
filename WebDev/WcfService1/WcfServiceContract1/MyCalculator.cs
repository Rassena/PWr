using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WcfServiceContract1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in both code and config file together.
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class MyCalculator : ICalculator
    {
        double sum;
        public double Add(double n1, double n2)
        {
            double result = n1 + n2;
            Console.WriteLine("Wywolano Add liczb: " + n1 + " i " + n2 + ". Otrzymano wynik: " + result);
            return result;
        }

        public double Multiply(double n1, double n2)
        {

            double result = n1 * n2;
            Console.WriteLine("Wywolano Multiply liczb: " + n1 + " i " + n2 + ". Otrzymano wynik: " + result);
            return result;

        }

        public double Sub(double n1, double n2)
        {
            double result = n1 - n2;
            Console.WriteLine("Wywolano Sub liczb: " + n1 + " i " + n2 + ". Otrzymano wynik: " + result);
            return result;
        }

        public double Sumarize(double n1)
        {
            sum += n1;
            return sum;
        }
    }
}
