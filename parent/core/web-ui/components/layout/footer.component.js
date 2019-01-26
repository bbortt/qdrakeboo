import React from 'react'
import {connect} from 'react-redux'

require('./footer.scss')

class Footer extends React.Component {

  render() {
    return (
      <footer>
        <div className='text-center'>
          Made with &hearts; | Developed on <a href='https://github.com/bbortt/my-little-streaming-dream'>GitHub</a>
        </div>
      </footer>
    )
  }
}

export default connect()(Footer)
