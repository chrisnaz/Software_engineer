<template>
  <div class="login" >
     <!--<p v-if="loginError">{{ loginError }}</p>
    <p v-if="accessToken">Login Successful</p>
    <form @submit.prevent="loginSubmit">
      <input type="username" placeholder="Username" v-model="username">
      <input type="password" placeholder="Password" v-model="password">
      <button type="submit">Login</button>
    </form>-->
    
        <v-layout justify-center style="margin-top: 100px">

          <v-flex sm3>

            <v-img :src="require('../assets/etadelard_logo.png')"
            height=94
            width=415
            style="margin-bottom: 20px"></v-img>

              <h4 class="red--text" v-if="loginError"> Wrong password or username, please try again</h4>
    <p v-if="accessToken">Login Successful</p>

            <h2 class="pink_custom--text font-weight-bold" > Login </h2>
              <v-card ref="form" flat style="margin-top: 20px">
                      <v-text-field
                        ref="username"
                        v-model="username"
                        label="Username"
                        :rules="[() => !!username || 'This field is required']"
                        :error-messages="errorMessages"
                         @blur="$v.username.$touch()"
                        required
                        box
                        flat
                        color="pink_custom"
                        background-color="light_grey_background"
                      ></v-text-field>

                <v-text-field
                  ref="password"
                  v-model="password"
                  type="password"
                  :rules="[
                    () => !!password || 'This field is required',
                    //() => password && password.length >= 8 || 'Minimum 8 characters',
                    //passwordCheck
                    ]"
                  label="Password"
                  required
                  box
                  flat
                  color="pink_custom"
                  background-color="light_grey_background"
                ></v-text-field>

                <!--<p class="text-lg-right" > 
                  <v-btn flat class="pink_custom--text white" @click="()=>{password_popup = true}" > Forgot password ? </v-btn>
                </p>-->
                
                
                <p class="text-lg-right"> 
                  <span>Not yet signed up ?</span>
                  <a href="/signup"
                  style="text-decoration: none"
                  class="pink_custom--text"> Create your account</a>
                </p>
                
              
              <v-card-actions>
                <v-btn round outline flat style="text-decoration: none;" class="grey" to="/">Cancel</v-btn>
                <v-spacer></v-spacer>
                <v-slide-x-reverse-transition>
                 <!-- <v-tooltip
                    v-if="formHasErrors"
                    left
                  >
                    <template v-slot:activator="{ on }">
                      <v-btn
                        icon
                        class="my-0"
                        @click="resetForm"
                        v-on="on"
                      >
                        <v-icon>refresh</v-icon>
                      </v-btn>
                    </template>
                    <span>Refresh form</span>
                  </v-tooltip>-->
                </v-slide-x-reverse-transition>
                
                <v-btn 
                  round 
                  depressed 
                  color="pink_custom white--text" 
                  @click="loginSubmit"
                  :disabled='!isLoginComplete'>Login</v-btn>
              </v-card-actions>

            </v-card>
          </v-flex>
        </v-layout>

        <!-- Change password popup -->
        <div class="text-xs-center">
          <v-dialog
            v-model="password_popup"
            width="500"
          >
            <v-card>
              <v-card-title
                class="pink_custom--text title font-weight-bold"
              >
                Change password
              </v-card-title>

              <v-card-text>
                  <v-flex>
                      <v-text-field
                          label="Old password"
                          type="password"
                          required
                          box
                          flat
                          color="pink_custom"
                          background-color="light_grey_background"
                          class="save-name"
                          v-model="old_password"
                      ></v-text-field>

                  </v-flex>
                  <v-flex>
                      <v-text-field
                          label="New password"
                          type="password"
                          required
                          box
                          flat
                          color="pink_custom"
                          background-color="light_grey_background"
                          class="save-name"
                          v-model="new_password"
                      ></v-text-field>

                  </v-flex>
                  <v-flex>
                      <v-text-field
                          label="Confirm new password"
                          type="password"
                          required
                          box
                          flat
                          color="pink_custom"
                          background-color="light_grey_background"
                          class="save-name"
                          v-model="confirm_new_pass"
                      ></v-text-field>

                  </v-flex>
              </v-card-text>

              <v-divider class="footer_grey save-divider"></v-divider>

              <v-card-actions>
                  <v-btn round outline class="cancel-btn grey" flat @click="password_popup = false">Cancel</v-btn>
                <v-spacer></v-spacer>
                <v-btn
                  round 
                  depressed 
                  color="pink_custom white--text"
                  @click="()=>{password_popup = false}"
                  :disabled='!isChangePassComplete'
                >
                  Save
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
  </div>
</template>

<script>
 
 import { mapState, mapActions } from 'vuex';
 import { validationMixin } from 'vuelidate'
 import { required, maxLength /*, email*/ } from 'vuelidate/lib/validators'

  export default {
     mixins: [validationMixin],

    validations: {
      username: { required, maxLength: maxLength(20) },
    },
    data: () => ({
      
        username: '',
        password: '',
        password_popup: false,
        old_password: '',
        new_password: '',
        confirm_new_pass: ''
      
    }),
    computed: {
      ...mapState([
        'loggingIn',
        'loginError',
        'accessToken'
      ]),
       errorMessages () {
        const errors = []
        if (!this.$v.username.$dirty) return errors
        !this.$v.username.maxLength && errors.push('username must be at most 20 characters long')
        !this.$v.username.required && errors.push('username is required.')
        return errors
      },
      isChangePassComplete () {
        return this.old_password && this.new_password && this.confirm_new_pass;
      },
      isLoginComplete () {
        return this.username && this.password;
      }
      
    },
    methods: {
      ...mapActions([
        'doLogin',
        'doLogout'
      ]),
      loginSubmit() {
        this.doLogin({
          username: this.username,
          password: this.password
        });
      },
      logoutSubmit(){
        //router.push('/');
        this.doLogout({
          
          

        });
        
      }
    }
  }
</script>