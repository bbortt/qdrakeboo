// @node js (server.js)
module.exports = () => {
  const currentDate = new Date()

  return new Date(
    currentDate.getTime()
    - currentDate.getTimezoneOffset() * 60 * 1000
  )
}
