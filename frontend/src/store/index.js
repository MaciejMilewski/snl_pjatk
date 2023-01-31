import {createStore} from 'vuex'

import auth from './modules/auth/auth'
import views from './modules/views/views'

export default createStore({
    modules: {
        auth,
        views
    },
})