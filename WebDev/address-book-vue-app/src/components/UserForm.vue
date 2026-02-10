<template>
  <div id="user-form">
    <form @submit.prevent="handleSubmit">
      <label>Imię</label>
      <input
        v-model="user.firstName"
        type="text"
        :class="{ 'has-error': submitting && invalidFirstName }"
        @focus="clearStatus"
        @keypress="clearStatus"
      />
      <label>Nazwisko</label>
      <input
        v-model="user.lastName"
        type="text"
        :class="{ 'has-error': submitting && invalidLastName }"
        @focus="clearStatus"
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
      <button>Dodaj Użytkownika</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "user-form",
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      user: {
        firstName: "",
        lastName: "",
      },
    };
  },

  methods: {
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();
      //check form fields
      if (this.invalidFirstName || this.invalidLastName) {
        this.error = true;
        return;
      }
      this.$emit("add:user", this.user);
      //clear form fields
      this.user = {
        firstName: "",
        lastName: "",
      };
      this.error = false;
      this.success = true;
      this.submitting = false;
    },

    clearStatus() {
      this.success = false;
      this.error = false;
    },
  },
};
</script>
<style scoped>
form {
  margin-bottom: 2rem;
}

[class*="-message"] {
  font-weight: 500;
}
.error-message {
  color: #d33c40;
}
.success-message {
  color: #32a95d;
}
</style>
