<template>
  <div id="users-table">
    <table>
      <!-- ...thead... -->
      <tbody>
        <tr v-for="user in usersSource" :key="user.id">
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "users-table",
  props: {
    usersSource: Array,
  },
};
</script>
<style scoped></style>