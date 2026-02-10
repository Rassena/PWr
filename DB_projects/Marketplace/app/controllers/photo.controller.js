const Photo = require("../models/photo.model.js");

// Create and Save a new Photo
exports.create = (req, res) => {
    // Validate request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    // Create a Photo
    const photo = new Photo({
      Name: req.body.Name,
      Url: req.body.Url,
      Id: req.body.Id,
      AdId: req.body.AdId,
    });
  
    // Save Photo in the database
    Photo.create(photo, (err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while creating the Photo."
        });
      else res.send(data);
    });
  };


// Retrieve all Photos from the database.
exports.findAll = (req, res) => {
    Photo.getAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while retrieving Photos."
        });
      else res.send(data);
    });
  };

// Find a single Photo with a photoId
exports.findOne = (req, res) => {
    Photo.findById(req.params.photoId, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Photo with id ${req.params.photoId}.`
          });
        } else {
          res.status(500).send({
            message: "Error retrieving Photo with id " + req.params.photoId
          });
        }
      } else res.send(data);
    });
  };

// Retrieve Photos by adId
exports.findByAdId = (req, res) => {
  Photo.findByAdId(req.params.adId, (err, data) => {
    if (err) {
      if (err.kind === "not_found") {
        res.status(404).send({
          message: `Not found Photo with adId ${req.params.adId}.`
        });
      } else {
        res.status(500).send({
          message: "Error retrieving Photo with adId " + req.params.adId
        });
      }
    } else res.send(data);
  });
};

// Update a Photo identified by the photoId in the request
exports.update = (req, res) => {
    // Validate Request
    if (!req.body) {
      res.status(400).send({
        message: "Content can not be empty!"
      });
    }
  
    Photo.updateById(
      req.params.photoId,
      new Photo(req.body),
      (err, data) => {
        if (err) {
          if (err.kind === "not_found") {
            res.status(404).send({
              message: `Not found Photo with id ${req.params.photoId}.`
            });
          } else {
            res.status(500).send({
              message: "Error updating Photo with id " + req.params.photoId
            });
          }
        } else res.send(data);
      }
    );
  };

// Delete a Photo with the specified photoId in the request
exports.delete = (req, res) => {
    Photo.remove(req.params.photoId, (err, data) => {
      if (err) {
        if (err.kind === "not_found") {
          res.status(404).send({
            message: `Not found Photo with id ${req.params.photoId}.`
          });
        } else {
          res.status(500).send({
            message: "Could not delete Photo with id " + req.params.photoId
          });
        }
      } else res.send({ message: `Photo was deleted successfully!` });
    });
  };

// Delete all Photos from the database.
exports.deleteAll = (req, res) => {
    Photo.removeAll((err, data) => {
      if (err)
        res.status(500).send({
          message:
            err.message || "Some error occurred while removing all photos."
        });
      else res.send({ message: `All Photos were deleted successfully!` });
    });
  };