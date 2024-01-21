const jwt = require("jsonwebtoken");
const config = require("../config/auth.config.js");
const User = require("../models/mongo")

verifyToken = (req, res, next) => {
  let token = req.headers["x-access-token"];

  if (!token) {
    return res.status(403).send({
      message: "No token provided!"
    });
  }

  jwt.verify(token,
            config.secret,
            (err, decoded) => {
              if (err) {
                return res.status(401).send({
                  message: "Unauthorized!",
                });
              }
              req.userId = decoded.id;
              next();
            });
};

isAdmin = async (req, res, next) => {
  await checkContainsRol(next, res, req.userId, ["ADMIN"]);
};

isModerator = async (req, res, next) => {
  await checkContainsRol(next, res, req.userId, ["MODERATOR"]);
};

isModeratorOrAdmin = async (req, res, next) => {
  await checkContainsRol(next, res, req.userId, ["MODERATOR", "ADMIN"]);
};

async function checkContainsRol(next, res, userId, requiredRoles) {
  try{
    const user = await User.findOne({username: userId });

    if (!user) {
      return;
    }

    let anyRoleMatch = false;

    for (const reqRol of requiredRoles) {
      if (user.roles.includes(reqRol)) {
        anyRoleMatch = true;
        break;
      }
    }

    if(!anyRoleMatch){
      res.status(403).send({message: "Required roles: " + requiredRoles});
      return;
    }

    next();
  }catch (error) {
    res.status(400).send({message: error});
  }
}

const authJwt = {
  verifyToken: verifyToken,
  isAdmin: isAdmin,
  isModerator: isModerator,
  isModeratorOrAdmin: isModeratorOrAdmin
};
module.exports = authJwt;
