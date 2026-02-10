//current in work

const sql = require("./db.js");

// constructor
const Analytic = function(analytic) {
  this.Header = analytic.Header
};


Analytic.countAll = (tableName, result) => {
  sql.query(`SELECT COUNT(*) AS NumberOf${tableName} FROM ${tableName};`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("count: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Ad with the id
    result({ kind: "not_found" }, null);
  });
};

Analytic.countAdInCategory = result => {
  sql.query(`SELECT SubcategoryName ,Count(Price) as 'Count', Sum(Price) as 'Sum', (Sum(Price)/count(Price)) as 'Average' FROM ad  LEFT JOIN purchase ON ad.Id = purchase.adId group by SubCategoryName;
  `, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("ads: ", res);
    result(null, res);
  });
};

Analytic.countAdByPaid = result => {
  sql.query(`SELECT Paid ,Count(Price) as 'Count', Sum(Price) as 'Sum', (Sum(Price)/count(Price)) as 'Average' FROM ad  LEFT JOIN purchase ON ad.Id = purchase.adId group by Paid;
  `, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("ads: ", res);
    result(null, res);
  });
};

Analytic.countAdByExpired = result => {
  sql.query(`select (if(now()>DueDate,true,false)) as 'Expired',Count(Price) as 'Count',Sum(Price) as 'Sum', (Sum(Price)/count(Price)) as 'Average' from ad group by DueDate;

  `, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(null, err);
      return;
    }

    console.log("ads: ", res);
    result(null, res);
  });
};

Analytic.rationAd = (result) => {
  sql.query(`select ((SELECT COUNT(*)FROM ad WHERE Negotiable = true)/( SELECT COUNT(*)fROM ad)) AS AdNegotaible,
  ((SELECT COUNT(*)FROM ad WHERE Negotiable = false)/( SELECT COUNT(*)fROM ad)) AS AdNoNegotaible;`, (err, res) => {
    if (err) {
      console.log("error: ", err);
      result(err, null);
      return;
    }

    if (res.length) {
      console.log("count: ", res[0]);
      result(null, res[0]);
      return;
    }

    // not found Ad with the id
    result({ kind: "not_found" }, null);
  });
};




module.exports = Analytic;