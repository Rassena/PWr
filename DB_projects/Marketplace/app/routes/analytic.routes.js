// current in work


module.exports = app => {
    const analytics = require("../controllers/analytic.controller.js");
  
    // Calculate ration negotiable/noNegotiable
    app.get("/analytics/ration/", analytics.rationAd);

    // Show ad by Paid status 
    app.get("/analytics/count/ad/paid", analytics.countAdByPaid);

    // Show ad by Expire status 
    app.get("/analytics/count/ad/expire", analytics.countAdByExpired);

    // Count ended ad (paid or expired) 
    //app.get("/analytics/count/ad/ended", analytics.countAdEnded);

     // Count all rows in tableName eg ad
     app.get("/analytics/count/AdInCategory", analytics.countAdInCategory);

    // Count all rows in tableName eg ad
    app.get("/analytics/count/:tableName/all", analytics.countAll);

  };
