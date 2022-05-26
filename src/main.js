import Vue from "vue";
import App from "./App.vue";
import axios from "axios";
// import VueAxios from "vue-axios";
axios.defaults.baseURL = "http://localhost:8000/";
Vue.prototype.$axios = axios;
Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
}).$mount("#app");
