<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-5 d-flex align-items-stretch">
	  <div class="row flex-grow">
		<div class="col-12 grid-margin">
		  <div class="card">
			<div class="card-body">
			  <h4 class="card-title">Default form</h4>
			  <p class="card-description">
				Basic form layout
			  </p>
			  <form class="forms-sample">
				<div class="form-group">
				  <input type="email" class="form-control" placeholder="Enter email">
				</div>
				<div class="form-group">
				  <input type="password" class="form-control" placeholder="Password">
				</div>
				<button type="submit" class="btn btn-success mr-2">Submit</button>
				<button class="btn btn-light">Cancel</button>
			  </form>
			</div>
		  </div>
		</div>
		
	  </div>
	</div>
	<div class="col-md-7 grid-margin stretch-card">
	  <div class="card">
		<div class="card-body">
		  <h4 class="card-title">Input size</h4>
		  <p class="card-description">
			This is the default bootstrap form layout
		  </p>
		  <div class="form-group">
			<label>Large input</label>
			<input type="text" class="form-control form-control-lg" placeholder="Username" aria-label="Username">
		  </div>
		  <div class="form-group">
			<label>Default input</label>
			<input type="text" class="form-control" placeholder="Username" aria-label="Username">
		  </div>
		  <div class="form-group">
			<label>Small input</label>
			<input type="text" class="form-control form-control-sm" placeholder="Username" aria-label="Username">
		  </div>
		</div>
		<div class="card-body">
		  <h4 class="card-title">Selectize</h4>
		  <div class="form-group">
			<label for="exampleFormControlSelect1">Large select</label>
			<select class="form-control form-control-lg" id="exampleFormControlSelect1">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		  </div>
		  <div class="form-group">
			<label for="exampleFormControlSelect2">Default select</label>
			<select class="form-control" id="exampleFormControlSelect2">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		  </div>
		  <div class="form-group">
			<label for="exampleFormControlSelect3">Small select</label>
			<select class="form-control form-control-sm" id="exampleFormControlSelect3">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
		  </div>
		</div>
	  </div>
	</div>
  </div>