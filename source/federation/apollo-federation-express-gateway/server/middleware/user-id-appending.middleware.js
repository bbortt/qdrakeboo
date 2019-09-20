const USER_ID_HEADER_NAME = 'qdrakeboo-user-id';

const userIdAppendingMiddleware = (req, res, next) => {
  const requestHeader = req.headers[USER_ID_HEADER_NAME];

  if (requestHeader) {
    res.headers[USER_ID_HEADER_NAME] = requestHeader;
  }

  next()
};

module.exports = userIdAppendingMiddleware;
