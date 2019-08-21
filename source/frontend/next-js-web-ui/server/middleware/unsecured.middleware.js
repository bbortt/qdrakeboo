module.exports = handle => {
  return (req, res) => {
    if (req.user) {
      return res.redirect('/app')
    }

    return handle(req, res)
  }
}
