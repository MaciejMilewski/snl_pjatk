import {createApp} from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import Uikit from "uikit"
import Icons from 'uikit/dist/js/uikit-icons'
//Import uikit icons
Uikit.use(Icons);

createApp(App).use(store).use(router).mount('#tlh-trojmiejska-liga-hokeja')