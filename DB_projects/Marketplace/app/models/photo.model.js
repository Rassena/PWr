const sql = require("./db.js");

// constructor
const Photo = function (photo) {
  this.Name = photo.Name;
  this.Url = photo.Url;
  this.Id = photo.Id;
  this.AdId = photo.AdId;
};

Photo.create = (newPhoto, result) => {
  sql.query("INSERT INTO photo SET ?", newPhoto, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created photo: ", { id: res.insertId, ...newPhoto });
    result(null, { id: res.insertId, ...newPhoto });
  });
};

Photo.findById = (PhotoId, result) => {
  sql.query(`SELECT * FROM photo WHERE id = ${PhotoId}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found photo: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Photo with the id
    result({ kind: "not_found" }, null);
  });
};

Photo.findByAdId = (AdId, result) => {
  sql.query(`SELECT * FROM photo WHERE id = ${AdId}`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found photo: ", res);
      result(null, res);
      return;
    }

    // not found Photo with the id
    result({ kind: "not_found" }, null);
  });
};

Photo.getAll = result => {
  sql.query("SELECT * FROM photo", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("photos: ", res);
    result(null, res);
  });
};

Photo.updateById = (id, photo, result) => {
  sql.query(
    "UPDATE photo SET Name = ?, SET Url = ?, SET AdId = ? WHERE id = ?",
    [photo.Name, photo.Url, photo.AdId, id],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found Photo with the id
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated photo: ", { id: id, ...photo });
      result(null, { id: id, ...photo });
    }
  );
};

Photo.remove = (id, result) => {
  sql.query("DELETE FROM photo WHERE id = ?", id, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found Photo with the id
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted photo with id: ", id);
    result(null, res);
  });
};

Photo.removeAll = result => {
  sql.query("DELETE FROM photo", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} photos`);
    result(null, res);
  });
};

module.exports = Photo;