<template>
  <div id="app" class="small-container">
    <h1>Znajomi</h1>

    <person-form @add:person="addPerson" />
    <persons-table :personsSource="persons" />
    <user-form @add:user="addUser" />
    <users-table :usersSource="users" />
  </div>
</template>
<script>
import PersonsTable from "@/components/PersonsTable.vue";
import PersonForm from "@/components/PersonForm.vue";
import UsersTable from "@/components/UsersTable.vue";
import UserForm from "@/components/UserForm.vue";

export default {
  name: "app",
  components: {
    PersonsTable,
    PersonForm,
    UsersTable,
    UserForm,
  },
  data() {
    return {
      persons: [
        /*
        {
          id: 1,
          name: "Adam Słodowy",
          email: "adam.slodowy@zrobtosam.pl",
          phone: "+48 787 774 664",
        },
        {
          id: 2,
          name: "Michał Studencki",
          email: "ms@student.pwr.edu.pl",
          phone: "+48 600 565 454",
        },
        {
          id: 3,
          name: "Kamila Napokaz",
          email: "kami2003@h2.pl",
          phone: "+48 609 554 987",
        },
      */
      ],
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
    addPerson(person) {
      this.persons = [...this.persons, person];
    },
    async getPersons() {
      try {
        const response = await fetch(
          "https://jsonplaceholder.typicode.com/users"
        );
        const data = await response.json();
        this.persons = data;
      } catch (error) {
        console.error(error);
      }
    },
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
    this.getPersons();
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
