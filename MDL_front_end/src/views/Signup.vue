<template>
  <v-form>
 <v-layout class="mt-5" align-center justify-center collumn wrap>
    <v-flex align-self-center xs8 sm4 offset-sm2>
      <v-card flat >

         <img src="/etadelard_logo.png" width="415px" height="94px" alt="image trop cool du logo">
        <h3 class="pink_custom--text mt-3 mb-2">Create account</h3>
        
        <v-layout align-start justify-start wrap>
           
         
          <v-flex xs12 sm10>

            <!-- First Name -->
          <v-text-field box
                        flat 
                        color="pink_custom" 
                        background-color="light_grey_background" 
                        label="First Name" 
                        v-model="first_name"
                        required>
                        </v-text-field>

            <!-- Last Name -->
          <v-text-field box 
                        flat 
                        color="pink_custom" 
                        background-color="light_grey_background" 
                        label="Last Name"
                        v-model="last_name"
                        required>
                        </v-text-field>

            <!-- Email -->
          <v-text-field box 
                        flat 
                        color="pink_custom" 
                        background-color="light_grey_background" 
                        label="Email" 
                        v-model="email"
                        required>
                        </v-text-field>


            <!-- Username -->
          <v-text-field box
                        flat 
                        color="pink_custom" 
                        background-color="light_grey_background" 
                        label="Username" 
                        v-model="username"
                        required>
                        </v-text-field>

            <!-- Password -->
          <v-text-field v-model="password"
                        :append-icon="show1 ? 'visibility' : 'visibility_off'"
                        :rules="passwordrules"
                        :type="show1 ? 'text' : 'password'"
                        name="input-10-1"
                        hint="At least 8 characters"
                        counter
                        @click:append="show1 = !show1"
                        label="Password"
                        color="pink_custom" 
                        background-color="light_grey_background"
                        required>
                        </v-text-field>
            
              <!-- Password confirmation -->
           <v-text-field v-model="password_conf"
                        :append-icon="show2 ? 'visibility' : 'visibility_off'"
                        :hint='confpasswordrules()'
                        :type="show2 ? 'text' : 'password'"
                        name="input-10-1"
                        label="Password confirmation"
                        @click:append="show2 = !show2"
                        color="pink_custom" 
                        background-color="light_grey_background"
                        required>
                        </v-text-field>

            <!-- status --> 
            
          <v-menu full-width>
              <v-btn block flat slot="activator" class="fields_grey">
              
                <span> {{status}} </span>
                <v-icon right> expand_more </v-icon>
              </v-btn>
              
              <v-list>
                <v-list-tile v-for="item in items" :key="item.title" router @click=" status = item.title" > 
                  <v-list-tile-title v-model="statut"> {{ item.title}} </v-list-tile-title>
                </v-list-tile>
              </v-list>
              
            
          </v-menu>
            
            <!-- Establishment -->
          <v-text-field box 
                        flat 
                        color="pink_custom" 
                        background-color="light_grey_background" 
                        label="Establishmnent" 
                        v-model="establishment"
                        required
                        class="mt-3">
                        </v-text-field>
          </v-flex>
        </v-layout>

        <v-card-actions>
                <v-btn round outline flat style="text-decoration: none;" class="grey" to="/">Cancel</v-btn>
                <v-spacer></v-spacer>
                <v-slide-x-reverse-transition>
                </v-slide-x-reverse-transition>
                
                <v-btn class="pink_custom white--text" style="margin-right:100px"
              depressed 
              round
              required
              :disabled='!isSignUpComplete'
              @click="loginSignUp" > Sign Up </v-btn>

        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>

  </v-form>
 
</template>

<script>
 import { mapState, mapActions } from 'vuex';

export default {
   data: () => ({
    first_name:'',
    last_name: '',
    password: '',
    password_conf: '',
    establishement: '',
    status: 'Select your status',
    email: '',
    show1: false,
    show2: false,
    
    items: [
    { title: 'Professor' },
    { title: 'Researcher' },
    { title: 'Student' },
  
    ], 

    passwordrules: [
      v => !!v || 'Password is required',
      v => v.length >= 8
    ],

    

    

   

     }),
     computed: {
       isSignUpComplete () {
        return this.first_name && this.last_name && this.password && this.password_conf && this.email && (this.password === this.password_conf);
      }

      
     },
     
     methods: {

        confpasswordrules() {
          return (this.password === this.password_conf) ? 'password confirmed' : 'password must match'
        } ,      
      ...mapActions([
        'doSignUp'
      ]),
      loginSignUp() {
        this.doSignUp({
          username: this.username,
          password: this.password,
          passwordConfirm: this.password_conf,
          email: this.email,
          establishment: this.establishment,
          firstname: this.first_name,
          lastname: this.last_name,
          statut: this.status
        });
      },

    }
     }
    
  

</script>