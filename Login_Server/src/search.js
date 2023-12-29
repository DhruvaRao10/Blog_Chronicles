// function toggleSearch() {
//     var expand = document.getElementById("searchExpand");
//     var text = document.getElementById("searchText");
//     text.classList.toggle("expanded");
//     expand.classList.toggle("expanded");
//     if (text.classList.contains("expanded")) {
//       text.textContent = "×";
//     } else {
//       text.textContent = "Search";
//     }
//   }
  
//   async function submitSearch() {
//     var searchTerm = document.getElementById("searchInput").value;
  
//     try {
//       const response = await fetch('/search', {
//         method: 'POST',
//         headers: {
//           'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({ SearchString: searchTerm }),
//       });

//       console.log(response);
  
//       if (response.ok) {
//         const data = await response.json();
//         console.log("Search results:", data);
//         // Update your UI with the search results if needed
//       } else {
//         console.error("Search request failed:", response.statusText);
//       }
//     } catch (error) {
//       console.error("Error during search request:", error);
//     }
//   }

//   window.toggleSearch = toggleSearch ; 
//   window.submitSearch = submitSearch ; 
  



