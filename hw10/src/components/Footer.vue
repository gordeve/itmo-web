<template>
    <footer>
      <p><a href="/">Codeforces</a> &copy; 2022 by Mike Mirzayanov</p>
      <p>Total users: {{ userCount }} </p>
      <p>Posts created: {{ postCount }}</p>
    </footer>
</template>

<script>
export default {
    name: "Footer",
    props: ["userCount", "postCount"]
}
</script>

<style scoped>

</style>