export default store => next => action => {
  console.log(`dispatching ${action.type}`)
  const result = next(action)
  console.log(`next state: ${JSON.stringify(store.getState())}`)
  return result
}
