<div class="row">
<sec:ifAnyGranted roles="ROLE_FREE_USER">
  
  <div>Free</div>
</sec:ifAnyGranted>

<sec:ifAnyGranted roles="ROLE_PAID_USER">
   
  <div class="col-md-3 squareContainer">
    <div class="col-md-3 squareContainer">
      <div class="col-md-3 squareContainer">
        1.1
      </div>
      <div class="col-md-3 squareContainer">
        1.1
      </div>
    </div>
    <div class="col-md-3 squareContainer">
     2
    </div>
  </div>
</sec:ifAnyGranted>

</div>