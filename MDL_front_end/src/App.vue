<template>
  <v-app style="background:#FFFFFF">
    <Navbar/>
    
    <Footer />
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>
<style> *{ text-transform: none !important; } </style>
<!-- easter egg <style>
*{font-family: Jokerman ; color: yellowgreen ; font-weight: 900;}
</style> -->


<style type="text/css"> 
a:link 
{ 
text-decoration:none; 
} 
</style>
<script>

import Navbar from '@/components/Navbar'
import Footer from '@/components/Footer'

import axios from 'axios';
import { mapState, mapActions } from 'vuex';
export default {
  name: 'App',

  components: { Navbar, Footer },

  data () {
    return {
      //
    }
  },
   methods: {

    ...mapActions([
      'fetchAccessToken'
    ]),
  },created() {
    this.fetchAccessToken();

  },
}
</script>