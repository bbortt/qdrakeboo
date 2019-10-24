const actuator = require('express-actuator')

const actuatorConfig = {
  basePath: '/actuator',
  infoGitMode: 'simple',
}

const configureActuator = dev => {
  if (!dev) {
    actuatorConfig.infoGitMode = 'full'
  }

  return actuator(actuatorConfig)
}

module.exports = {
  configureActuator,
}
