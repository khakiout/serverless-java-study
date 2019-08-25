'use strict';
const express = require('express')
const hello = express()
const sls = require('serverless-http')

//Handle the GET endpoint on the root route /
hello.get('/', async (req, res, next) => {
 res.status(200).send('Hello everyone!')
})
hello.get('/world', async (req, res, next) => {
 res.status(200).send('Hello World!!')
})
hello.get('/earth', async (req, res, next) => {
 res.status(200).send('Hello Earth!!')
})
hello.get('/mars', async (req, res, next) => {
 res.status(200).send('Hello Mars!!')
})

module.exports.server = sls(hello)