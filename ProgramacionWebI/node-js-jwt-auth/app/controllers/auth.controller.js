const config = require("../config/auth.config");
const User = require("../models/mongo")
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");

const allowedRoles = ['ADMIN', 'USER', 'MODERATOR'];

exports.signup = (req, res) => {
    if(req.body.roles){
        req.body.roles.forEach(rol => {
          const exists = allowedRoles.findIndex(allowedRol => allowedRol === rol) !== -1;
          if(!exists){
              res.status(400).send({message: 'Unknown rol'});
          }
        });
    }

    const newUser = new User({
        username: req.body.username,
        email: req.body.email,
        password: bcrypt.hashSync(req.body.password, 8),  // In practice, you should hash passwords before saving them
        roles: req.body.roles
    });

    newUser.save().then(user => {
        res.status(200).send({message: 'User saved'});
    }).catch(err => {
        res.status(400).send({message: 'Error while saving user'});
    });
};

exports.signin = (req, res) => {
    User.findOne({username: req.body.username}).then(user => {
        if (!user) {
            return res.status(404).send({message: "User Not found."});
        }

        const passwordIsValid = bcrypt.compareSync(
            req.body.password,
            user.password
        );

        if (!passwordIsValid) {
            return res.status(401).send({accessToken: null, message: "Invalid Password!"});
        }

        const token = jwt.sign({ id: user.id },
            config.secret,
            {
                algorithm: 'HS256',
                allowInsecureKeySizes: true,
                expiresIn: 86400, // 24 hours
            });

        res.status(200).send({...user, accessToken: token});
    });
};
