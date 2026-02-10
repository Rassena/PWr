using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Security;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WcfServiceContract1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract(ProtectionLevel = ProtectionLevel.None)]
    public interface ICalculator
    {
        [OperationContract]
        double Add(double n1, double n2);

        [OperationContract]
        double Sub(double n1, double n2);

        [OperationContract]
        double Multiply(double n1, double n2);

        [OperationContract]
        double Sumarize(double n1);


        // TODO: Add your service operations here
    }
}