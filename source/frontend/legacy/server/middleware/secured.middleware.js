module.exports = () => {
  return (req, res, next) => {
    if (req.user) {
      return next()
    }

    return res.redirect('/login')
  }
}
