const sql = require("./db.js");

// constructor
const User = function(user) {
  this.Name = user.Name;
  this.LastName = user.LastName;
  this.BirthDate = user.BirthDate;
  this.Email = user.Email;
  this.Password = user.Password;
  this.SSO = user.SSO;
};

User.create = (newUser, result) => {
  sql.query("INSERT INTO user SET ?", newUser, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    console.log("created user: ", { sso: res.insertsso, ...newUser });
    result(null, { sso: res.insertsso, ...newUser });
  });
};

User.findBySso = (sso, result) => {
  sql.query(`SELECT * FROM user WHERE SSO = "${sso}"`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("found user: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found User with the Sso
    result({ kind: "not_found" }, null);
  });
};

User.getAll = result => {
  sql.query("SELECT * FROM user", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("users: ", res);
    result(null, res);
  });
};

User.updateBySso = (sso, user, result) => {
  sql.query(
    'UPDATE user SET Name = "?", LastName = "?",  BirthDate = ?, Email = "?", Password = "?" WHERE SSO = "?"',
    [user.Name, user.LastName, user.BirthDate, user.Email,user.Password, user.SSO, sso],
    (err, res) => {
      if (err) {
        console.log("error: ", err);
        result(null, err);
        return;
      }

      if (res.affectedRows == 0) {
        // not found User with the Sso
        result({ kind: "not_found" }, null);
        return;
      }

      console.log("updated user: ", { sso: sso, ...user });
      result(null, { sso: sso, ...user });
    }
  );
};

User.remove = (sso, result) => {
  sql.query("DELETE FROM user WHERE SSO = ?", sso, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    if (res.affectedRows == 0) {
      // not found User with the sso
      result({ kind: "not_found" }, null);
      return;
    }

    console.log("deleted user with SSO: ", sso);
    result(null, res);
  });
};

User.removeAll = result => {
  sql.query("DELETE FROM user", (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log(`deleted ${res.affectedRows} users`);
    result(null, res);
  });
};

module.exports = User;