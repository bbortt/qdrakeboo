// @node js (server.js)
module.exports.getDateWithTimezoneOffset = () => {
  const currentDate = new Date()

  return new Date(
    currentDate.getTime()
    - currentDate.getTimezoneOffset() * 60 * 1000
  )
}
