const express = require('express');
const path = require('path');
const RosefireTokenVerifier = require('rosefire-node');
const rosefireSecret =  process.env.ROSEFIRE_SECRET;
const rosefire = new RosefireTokenVerifier(rosefireSecret);

module.exports.auth = function (req, res, next) {
    const token = req.headers.authorization;

    if (token) {
        rosefire.verify(token, (err, authData) => {
            if (err) {
                res.status(401);
                return res.json({
                    success: false,
                    error: 'Not authorized'
                });
            } else {
                req.authData = authData;
                next();
            }
        });
    } else {
        return res.status(401).send({
            success: false,
            error: 'Not authorized'
        });
    }
};
