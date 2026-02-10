module.exports = app => {
    const photos = require("../controllers/photo.controller.js");
  
    // Create a new Photo
    app.post("/photos", photos.create);
  
    // Retrieve all Photos
    app.get("/photos", photos.findAll);

    // Retrieve a single Photo with photoId
    app.get("/photos/ad/:adId", photos.findByAdId);
  
    // Retrieve a single Photo with photoId
    app.get("/photos/:photoId", photos.findOne);
  
    // Update a Photo with photoId
    app.put("/photos/:photoId", photos.update);
  
    // Delete a Photo with photoId
    app.delete("/photos/:photoId", photos.delete);
  
    // Create a new Photo
    app.delete("/photos", photos.deleteAll);
  };