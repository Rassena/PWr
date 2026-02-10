<template>
  <div id="users" class="small-container">
    <h1>Użytkownicy</h1>
    <user-form @add:user="addUser" />
    <users-table :usersSource="users" />
  </div>
</template>
<script>
import UsersTable from "@/components/UsersTable.vue";
import UserForm from "@/components/UserForm.vue";

export default {
  name: "users",
  components: {
    UsersTable,
    UserForm,
  },
  data() {
    return {
      users: [
        {
          id: 1,
          firstName: "Adam",
          lastName: "Słodowy",
        },
        {
          id: 2,
          firstName: "Michał",
          lastName: "Studencki",
        },
        {
          id: 3,
          firstName: "Kamila",
          lastName: "Napokaz",
        },
      ],
    };
  },
  methods: {
    addUser(user) {
      this.users = [...this.users, user];
    },
    async getUsers() {
      try {
        const response = await fetch("http://localhost:8080/get/users");
        const data = await response.json();
        this.users = data;
      } catch (error) {
        console.error(error);
      }
    },
  },

  mounted() {
    this.users();
  },
};
</script>
<style>
button {
  background: #009435;
  border: 1px solid #009435;
}
.small-container {
  max-width: 680px;
}
</style>
