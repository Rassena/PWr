using Grpc.Core;
using Mygrpcproto;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace gRPCserver
{
    class MyGrpcSrvImpl :MyGrpcSrv.MyGrpcSrvBase
    {
        public override Task<addIntReply> addInt(AddIntRequest req, ServerCallContext ctx)
        {
            string comment;
            int result;

            result = req.Num1 + req.Num2;

            if (result >= 0)
                comment = "Positive Value: result =  ";
            else
                comment = "Negative Value: result =  ";


            return Task.FromResult(new addIntReply
            {
                Result = result,
                Comment = comment
            });



        }
    }
}
