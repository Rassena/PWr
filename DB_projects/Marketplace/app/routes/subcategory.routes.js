module.exports = app => {
    const subcategorys = require("../controllers/subcategory.controller.js");
  
    // Create a new Subcategory
    app.post("/subcategorys", subcategorys.create);
  
    // Retrieve all Subcategorys
    app.get("/subcategorys", subcategorys.findAll);
  
    // Retrieve a single Subcategory with SubcategoryName
    app.get("/subcategorys/:subcategoryName", subcategorys.findOne);
  
    // Update a Subcategory with SubcategoryName
    app.put("/subcategorys/:subcategoryName", subcategorys.update);
  
    // Delete a Subcategory with SubcategoryName
    app.delete("/subcategorys/:subcategoryName", subcategorys.delete);
  
    // Create a new Subcategory
    app.delete("/subcategorys", subcategorys.deleteAll);
  };