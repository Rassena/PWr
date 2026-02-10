const Ad = require("../models/ad.model.js");

// Create and Save a new Ad
exports.create = (req, res) => {
    // Validate request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    // Create a Ad
    const ad = new Ad({
      Header: req.body.Header,
      Description: req.body.Description,
      Price: req.body.Price,
      Negotiable: req.body.Negotiable,
      PostDate: req.body.PostDate,
      DueDate: req.body.DueDate,
      Id: req.body.Id,
      SubCategoryName: req.body.SubCategoryName,
      UserSso: req.body.UserSso,
    });
  
    // Save Ad in the database
    Ad.create(ad, (err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while creating the Ad."
        });
      else res.send(data);
    });
  };

// Retrieve Ads by dates.
exports.getByDates = (req, res) => {
    Ad.findByDates(req.params.postDate1, req.params.postDate2, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found `
          });
        } else {
          res.status(500).send({
            message: "Error retrieving Ads "
          });
        }
      } else res.send(data);
    });
  };
  

// Retrieve all Ads from the database.
exports.findAll = (req, res) => {
  Ad.getAll((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving ads."
      });
    else res.send(data);
  });
};

// Retrieve all active Ads.
exports.getAllActive = (req, res) => {
  Ad.getAllActive((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving ads."
      });
    else res.send(data);
  });
};

// Retrieve all paid Ads.
exports.getAllPaid = (req, res) => {
  Ad.getAllPaid((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving ads."
      });
    else res.send(data);
  });
};

// Retrieve all expired Ads.
exports.getAllExpired = (req, res) => {
  Ad.getAllExpired((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving ads."
      });
    else res.send(data);
  });
};


// Find a ads by price between price1 and price2
exports.findBetweenPrice = (req, res) => {
  Ad.findByPrice(req.params.price1,req.params.price2, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found `
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Ads "
        });
      }
    } else res.send(data);
  });
};

// Retrieve Ads from the database by pages.
exports.getPage = (req, res) => {
  Ad.getPage(req.params.page, req.params.inPage, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Ad with page ${req.params.page}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Ad with page " + req.params.page
        });
      }
    } else res.send(data);
  });
};

// Find a single Ad by a adId
exports.findOne = (req, res) => {
    Ad.findById(req.params.adId, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Ad with id ${req.params.adId}.`
          });
        } else {
          res.status(500).send({
            message: "Error retrieving Ad with id " + req.params.adId
          });
        }
      } else res.send(data);
    });
  };

// Find  all Ads with photo url
exports.findAllWithPhoto = (req, res) => {
  Ad.findAlldWithPhoto((err, data) => {
    if (err)
    res.status(500).send({
      message:
        err.message || "Some error occurred while retrieving ads."
    });
  else res.send(data);
});
};


// Update a Ad identified by the adId in the request
exports.update = (req, res) => {
    // Validate Request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    Ad.updateById(
      req.params.adId,
      new Ad(req.body),
      (err, data) => {
        if (err) {
          if (err.kind === "not_found") {
            res.status(404).send({
              message: `Not found Ad with id ${req.params.adId}.`
            });
          } else {
            res.status(500).send({
              message: "Error updating Ad with id " + req.params.adId
            });
          }
        } else res.send(data);
      }
    );
  };

// Delete a Ad with the specified adId in the request
exports.delete = (req, res) => {
    Ad.remove(req.params.adId, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Ad with id ${req.params.adId}.`
          });
        } else {
          res.status(500).send({
            message: "Could not delete Ad with id " + req.params.adId
          });
        }
      } else res.send({ message: `Ad was deleted successfully!` });
    });
  };

// Delete all Ads from the database.
exports.deleteAll = (req, res) => {
    Ad.removeAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while removing all ads."
        });
      else res.send({ message: `All Ads were deleted successfully!` });
    });
  };


// Retrieve all Negotiable Ads from the database.
exports.getNegotiable = (req, res) => {
  Ad.getNegotiable((err, data) => {
    if (err)
      res.status(500).send({
        message:
          err.message || "Some error occurred while retrieving ads."
      });
    else res.send(data);
  });
};

// Find a Ads with a subCategoryName
exports.getBySubCategory = (req, res) => {
  Ad.getBySubCategory(req.params.subCategoryName, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Ad with sub category ${req.params.subCategoryName}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Ad with sub category " + req.params.subCategoryName
        });
      }
    } else res.send(data);
  });
};


// Find all Ads with userSso 
exports.findByUserSso = (req, res) => {
  Ad.findByUserSso(req.params.userSso, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Ad with user id ${req.params.userSso}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Ad user id " + req.params.userSso
        });
      }
    } else res.send(data);
  });
};


// Find all  paid Ads by UserSso 
exports.getAllPaidByUserSso = (req, res) => {
  Ad.getAllPaidByUserSso(req.params.userSso, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Ad with user sso ${req.params.UserSso}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Ad user sso " + req.params.UserSso
        });
      }
    } else res.send(data);
  });
};

// Find all  expired Ads by UserSso 
exports.getAllExpiredByUserSso = (req, res) => {
  Ad.getAllExpiredByUserSso(req.params.userSso, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Ad with user sso ${req.params.UserSso}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Ad user sso " + req.params.UserSso
        });
      }
    } else res.send(data);
  });
};


// Find all  active Ads by UserSso 
exports.getAllActiveByUserSso = (req, res) => {
  Ad.getAllActiveByUserSso(req.params.userSso, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Ad with user sso ${req.params.userSso}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Ad user sso " + req.params.userSso
        });
      }
    } else res.send(data);
  });
};