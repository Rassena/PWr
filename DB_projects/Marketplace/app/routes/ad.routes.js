module.exports = app => {
    const ads = require("../controllers/ad.controller.js");
  
    // Create a new Ad
    app.post("/ads", ads.create);
  
    // Retrieve all Ads
    app.get("/ads", ads.findAll);

    // Retrieve all active Ads
    app.get("/ads/active", ads.getAllActive);

    // Retrieve active Ads by userSso
    app.get("/ads/active/:userSso", ads.getAllActiveByUserSso);

    // Retrieve all expired Ads
    app.get("/ads/expired", ads.getAllExpired);

    // Retrieve all paid Ads
    app.get("/ads/paid", ads.getAllPaid);

    // Retrieve expired Ads by userSso
    app.get("/ads/expired/:userSso", ads.getAllExpiredByUserSso);

    // Retrieve paid Ads by userSso
    app.get("/ads/paid/:userSso", ads.getAllPaidByUserSso);


    // Retrive all negotiable ads
    app.get("/ads/negotiable",ads.getNegotiable);

    
    // Retrive all negotiable ads
    //app.get("/ads/negotiable",ads.getNegotiable);


    // Retrive ads by PostDates
    app.get("/ads/date/:postDate1/:postDate2",ads.getByDates);

    //Retrieve inPage Ads by page 
    app.get("/ads/page/:page/:inPage", ads.getPage);

    //Retrieve Ads by subcategory name
    app.get("/ads/subcategory/:subCategoryName", ads.getBySubCategory);

    //Retrieve Ads by user Sso
    app.get("/ads/user/:userSso", ads.findByUserSso);

    // Retrieve all Ads with photo url
    app.get("/ads/photo", ads.findAllWithPhoto);

    // Retrieve a single Ad by adId
    app.get("/ad/:adId", ads.findOne);

    // Update a Ad with adId
    app.put("/ad/:adId", ads.update);
  
    // Delete a Ad with adId
    app.delete("/ad/:adId", ads.delete);
  
    // Delete all ads
    app.delete("/ads", ads.deleteAll);


    // Retrive Ads beetwen price price1 and price2
    app.get("/ads/:price1/:price2",ads.findBetweenPrice);

    






  };