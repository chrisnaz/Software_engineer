import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import VueRouter from 'vue-router';
import router from './router'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    accessUsername:null,
    accessToken: null,
    accessID: null,
    loggingIn: false,
    loginError: null
  },
  mutations: {
    loginStart: state => state.loggingIn = true,
    loginStop: (state, errorMessage) => {
      state.loggingIn = false;
      state.loginError = errorMessage;
    },
    updateAccessToken: (state, accessToken) => {
      state.accessToken = accessToken;
    },
    updateAccessUsername: (state, accessUsername) => {
      state.accessUsername = accessUsername;
      
    },
    updateAccessID: (state, accessID) => {
      state.accessID = accessID;
      
    }
  },
  actions: {
    //Login an user
    doLogin({ commit }, loginData) {
      console.log(loginData);
      commit('loginStart');

      // http://localhost:8080/auth/signin
      //  http://mdl-std05.info.fundp.ac.be:8181/mdl_web/auth/signin
      axios.post('http://mdl-std05.info.fundp.ac.be:8181/mdl_web/auth/signin', {
        ...loginData
      })
      .then(response => {
        router.push('/');
        localStorage.setItem('accessToken', response.data.token);
        localStorage.setItem('accessUsername', response.data.username);
        localStorage.setItem('accessID', response.data.id);
        commit('loginStop', null);
        commit('updateAccessToken', response.data.token);
        commit('updateAccessUsername', response.data.username);
        commit('updateAccessID', response.data.id);
      })
      .catch(error => {
        commit('loginStop', error.response.data.error);
        commit('updateAccessToken', null);
        commit('updateAccessUsername', null);
        commit('updateAccessID', null);
      })

    },
    //Logout an user
    doLogout({commit}){
      commit('loginStop');
      localStorage.removeItem('accessToken');
      localStorage.removeItem('accessUsername');
      commit('updateAccessToken', null);
      commit('updateAccessUsername', null);
    },
    //Create an user
    doSignUp({ commit }, loginSignUp) {

      //http://localhost:8080/Users
      //http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Users
      axios.post('http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Users', {
        ...loginSignUp
      });
      router.push('/login');
      //this.$router.push({ name: '/login' });
     
    },
    //Get derived Tag from articles
    doSend({ commit }, testval) {
      
      //http://localhost:8080/Derive
      //http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Derive
      axios.post('http://mdl-std05.info.fundp.ac.be:8181/mdl_web/Derive ', {
        ...testval
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.log(response);
      })

    },
    fetchAccessToken({ commit }) {
      commit('updateAccessToken', localStorage.getItem('accessToken'));
    },
    doDerive({ commit }, loginDerive) {
      console.log("send=> " + loginDerive);

      //http://localhost:8080/auth/signin
      // http://mdl-std05.info.fundp.ac.be:8181/Derive
      axios.post('http://mdl-std05.info.fundp.ac.be:8181/Derive', {
        ...loginDerive
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
      })

    }
   
  }
})
